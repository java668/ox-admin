// Harbor私服地址
def harbor_url = "ccr.ccs.tencentyun.com"
// Harbor的项目名称
def harbor_project_name = "java668"
// 构建版本的名称
def PROJECT_NAME = "ox-admin"

def port = 8080
// tag
def tag = "latest"
// 镜像名称
def imageName = "${PROJECT_NAME}:${tag}"
// 链接
def link = "--link mysql_pro"
// 挂载目录
def mount = "-v /usr/local/docker_data/mblog/storage:/app/mblog/storage -v /usr/local/docker_data/mblog/logs:/app/mblog/logs"

pipeline {
    agent any
    stages {
        stage('拉取代码') {
            steps {
                echo '=============拉取代码============='
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '38dd7ab4-5c12-4624-b2d3-ea1d43e53d6a', url: 'git@github.com:java668/ox-admin.git']])
                echo '=============拉取代码完成============='
            }
        }
        stage('编译构建') {
            steps {
                echo '=============编译构建============='
                // 编译，打包，构建本地镜像
                sh 'mvn -f ox-admin-application -DskipTests=true -Pprod clean package dockerfile:build'
                echo '=============编译构建完成============='
            }
        }
        stage('项目部署') {
            steps {
                // 给镜像打标签
                echo "=============给镜像打标签=============${imageName}======="
                sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"
                echo '=============给镜像打标签完成============='

                withCredentials([usernamePassword(credentialsId: '3089b821-709c-4dcf-a4b8-d7ff0090a253', passwordVariable: 'password', usernameVariable: 'username')]) {
                    // 登录
                    sh "docker login -u ${username} -p ${password} ${harbor_url}"
                    // 上传镜像
                    sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}"
                }

                //删除本地镜像
                sh "docker rmi -f ${imageName}"
                sh "docker rmi -f ${harbor_url}/${harbor_project_name}/${imageName}"

                // 部署
                withCredentials([usernamePassword(credentialsId: 'c86b0e91-f186-47cc-8e1c-310efd56399b', passwordVariable: 'password', usernameVariable: 'username')]) {
                    // some block
                    // 环境变量参数
                    echo "执行的命令:/opt/jenkins_shell/deploy1.sh $harbor_url $harbor_project_name $PROJECT_NAME $tag $port \"-e JAVA_OPTS=\'-Djava668.mysql.host=mysql_pro -Djava668.mysql.username=${username} -Djava668.mysql.password=${password}\' ${link} ${mount}\""
                    sshPublisher(publishers: [sshPublisherDesc(
                    configName: '81.69.220.64',
                    transfers: [sshTransfer(cleanRemote: false, excludes: '',
                                            execCommand: "/opt/jenkins_shell/deploy.sh $harbor_url $harbor_project_name $PROJECT_NAME $tag $port \"-e JAVA_OPTS=\'-Djava668.mysql.host=mysql_pro -Djava668.mysql.username=${username} -Djava668.mysql.password=${password}\' ${link} ${mount}\"",
                                            execTimeout: 120000,
                                            flatten: false,
                                            makeEmptyDirs: false,
                                            noDefaultExcludes: false,
                                            patternSeparator: '[, ]+',
                                            remoteDirectory: '',
                                            remoteDirectorySDF: false,
                                            removePrefix: '',
                                            sourceFiles: '')],
                    usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                }

            }
        }
    }
}