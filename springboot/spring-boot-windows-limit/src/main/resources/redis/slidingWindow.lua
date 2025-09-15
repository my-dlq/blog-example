-- ====== 滑动时间窗口计算 Lua 脚本 ======
-- KEYS[1]: zset集合Key名称
-- ARGV[1]: 当前时间(窗口终点)
-- ARGV[2]: 事件窗口大小
-- ARGV[3]: 限流阈值
-- ARGV[4]: 随机值(由于ZSET中的值不能重复，所以需要加上随机值来保证唯一性)

-- 参数设置
local key = KEYS[1]
local current_time = tonumber(ARGV[1])
local window_length = tonumber(ARGV[2])
local limit_number = tonumber(ARGV[3])
local random_val = ARGV[4]

-- 计算窗口的起始时间(窗口起点)
local window_start = current_time - window_length

-- 移除窗口时间之前的全部请求次数记录
redis.call('zremrangebyscore', key, '-inf', window_start)

-- 统计当前事件窗口中的总请求次数
local current_requests = redis.call('zcard', key)

-- 如果当前窗口内请求次数小于阈值，则添加当前时间到窗口内并返回1，否则返回0
if current_requests < limit_number then
    -- 添加当前请求到窗口内，并且设置score为当前事件，设置value为随机值
    redis.call('zadd', key, current_time, random_val)
    -- 为了避免Key永不过期，这里设置Key的过期时间为1小时，具体过期时间可以业务调整
    redis.call('expire', key, 3600)
    return 1
else
    return 0
end
