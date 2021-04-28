package mydlq.club.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam(name = "file") MultipartFile file) {
        String filepath = "e:" + File.separator;
        String filename = file.getOriginalFilename();
        log.info("文件名称：{}", filename);
        try (FileOutputStream out = new FileOutputStream(filepath + filename)) {
            out.write(file.getBytes());
            out.flush();
        } catch (Exception e) {
            log.error("", e);
            return "上传失败！";
        }
        return "上传成功！";
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() {
        // 获取指定目录下的第一个文件
        FileSystemResource file = new FileSystemResource("D://test.xlsx");
        // 设置文件长度
        long fileLength = 0L;
        // 创建文件输入流对象
        InputStreamResource inputStreamResource = null;
        // 创建 Headers 对象
        HttpHeaders headers = new HttpHeaders();
        try {
            // 获取文件长度
            fileLength = file.contentLength();
            // 获取文件输入流
            inputStreamResource = new InputStreamResource(file.getInputStream());
            // 设置 Headers
            headers.add("Expires", "0");
            headers.add("Pragma", "no-cache");
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试文件.xlsx", "UTF-8"));
        } catch (IOException e) {
            log.error("", e);
        }
        // 返回下载内容
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileLength)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(inputStreamResource);
    }

}
