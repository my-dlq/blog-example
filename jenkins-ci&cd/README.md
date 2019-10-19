# Jenkins 与 Kubernetes 的 CI 与 CD 示例文件

这个项目是 Jenkins CI\CD 演示过程中的一些配置文件和测试示例项目，详情请参考我的个人博客地址： http://www.mydlq.club/article/47/

文件介绍：

- 测试的示例项目：博客中演示的示例项目。
- settings.xml：全局 Maven 配置文件。
- Dockerfile：docker 镜像构建脚本文件。
- pipeline.groovy：Jenkins 的脚本式流水线脚本，使用 Groovy 语法。
- deployment.yaml：示例项目的 Kubernetes 部署文件模板，里面配置了一些可替换变量值。
