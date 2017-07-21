pipeline {
  agent any
  stages {
    stage('param test') {
      steps {
        sh '''cd /var/lib/jenkins/workspace/np

mvn clean test -Durl=yamaica.com -Dname=PEDRO
mvn test
mvn test -Durl=google.com
mvn test -D url=meta.ua -Dname=HULIO

'''
      }
    }
  }
}