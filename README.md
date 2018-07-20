# CLog

在project的gradle中添加repositories：
maven {
	url "https://dl.bintray.com/yaochuan/android"
}

在app的dependencies中增加：
compile 'cn.yc.clog:clog:1.1'

初始化：
CLog.open();

设置全局TAG
CLog.setGlobalTag("Installer:");
