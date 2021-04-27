# KotlinTest

## kt日常记录
## 功能点收集

### 2021/3/30 SparseArray + 责任链 处理首页弹窗过多

### 2021/4/27 service下载更新相关
首先是urlconnect ssl证书问题，因为downloadmanager无法设置证书好像
安卓6.0世纪在manager remove时如果没有下载任务时会crash
android:networkSecurityConfig="@xml/network_security_config"问题
压缩解压缩问题 文件夹的问题
然后多次安装都去解压删除造成了解析包错误，在组件里面出问题了，先判断是否存在
