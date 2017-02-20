项目说明：

一、Android动画总结
1.逐帧动画
2.补间动画
3.属性动画


二、属性动画运用示例：
平面3D旋转切换View--PlaneRevolutionLayout

三、上传Android library 到jcenter
1.创建bintray官网账号
注册地址：https://bintray.com/signup
当然你也可以使用GitHub账号地址注册，这里需要注意邮箱地址不能是QQ邮箱地址
2.创建jcenter仓库
3.创建仓库的package
4.在project项目的gradle文件中添加上传插件
 dependencies {
        classpath 'com.android.tools.build:gradle:2.2.3'
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7.1'
        classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
    }
5.在需要上传的库Module里添加上传命令和参数
如下：
apply plugin: 'com.android.library'
apply plugin: 'com.github.dcendents.android-maven'
apply plugin: 'com.jfrog.bintray'

def siteUrl = 'https://github.com/limushan/AnimationSummary'                        // 配置项目地址
def gitUrl = 'https://github.com/limushan/AnimationSummary.git'
group = "com.possess"                                                                   //Maven Group ID for the artifact
version = "1.0.0"                                                                       //版本号
install {
    repositories.mavenInstaller {
        // This generates POM.xml with proper parameters
        pom {
            project {
                packaging 'aar'
                name 'Plane Revolution For Android'                                   // 配置项目说明
                url siteUrl
                // Set your license
                licenses {
                    license {
                        name 'The Apache Software License, Version 2.0'
                        url 'http://www.apache.org/licenses/LICENSE-2.0.txt'
                    }
                }
                developers {
                    developer {
                        id 'possess'                                           // 配置你的昵称
                        name 'limushan'                                       // 用户名
                        email '1530422024@qq.com'                               // 邮箱地址
                    }
                }
                scm {
                    connection gitUrl
                    developerConnection gitUrl
                    url siteUrl
                }
            }
        }
    }
}

task sourcesJar(type: Jar) {
    from android.sourceSets.main.java.srcDirs
    classifier = 'sources'
}

task javadoc(type: Javadoc) {
    source = android.sourceSets.main.java.srcDirs
    classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
}

task javadocJar(type: Jar, dependsOn: javadoc) {
    classifier = 'javadoc'
    from javadoc.destinationDir
}

artifacts {
    // archives javadocJar
    archives sourcesJar
}

Properties properties = new Properties()
boolean isHasFile = false
if (project.rootProject.file('local.properties') != null){   //在local.properties文件配置bintray账号的用户名和apikey
    isHasFile = true
    properties.load(project.rootProject.file('local.properties').newDataInputStream())
}
bintray {
    user = isHasFile ? properties.getProperty("bintray.user") : System.getenv("bintray.user")
    key = isHasFile ? properties.getProperty("bintray.apikey") : System.getenv("bintray.apikey")
    configurations = ['archives']
    pkg {
        repo = "android"                                                          //配置你在jcenter上创建的仓库名
        name = "plane-revolution"                                                 // 配置项目名即你在仓库中创建的package名
        websiteUrl = siteUrl
        vcsUrl = gitUrl
        licenses = ["Apache-2.0"]
        publish = true
    }
}
6.依此执行gradlew install 和gradlew bintrayUpload命令（Mac使用./gradlew命令）
7.引用： compile CROUP_IP:ARTIFACT_ID:VERSION
例如本库的的地址：
compile 'com.possess:plane-revolution:1.0.0'
还需要加上下面这句话，添加你的bintray本地库地址，因为此时你的库还没有加入到jcenter中
maven {
             url 'https://dl.bintray.com/limushan/android/'
         }
8.加入jcenter库很简单：登录bintray账号，在你的项目中点击【Add to JCenter】发送信息提交
Bintray团队审核，审核通过会给你会邮件。此时就可以直接使用【compile 'com.possess:plane-revolution:1.0.0'】
引用您的项目了。




