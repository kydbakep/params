node {
    stage('Test of parameters') {
        git 'https://github.com/kydbakep/params.git'
        def environment = docker.build('tober_test_docker_build')
        environment.inside() {
            sh 'ls -a src/'

            def commandParams = ''
            if (env.AWIS_URL) { // Якщо не прийшов логін
                commandParams += "-Durl=${env.AWIS_URL}"
            }
            if (env.AWIS_LASTNAME) { // Якщо прийшло тільки прізвище
                commandParams += "-DlastName=${env.AWIS_LASTNAME}"
            }
            if (env.AWIS_LOGIN) { // Якщо прийшов тільки логін
                commandParams += " -Dname=${env.AWIS_LOGIN}"
            }

            sh "mvn test " + commandParams
//
//
//            if (env.AWIS_LASTNAME && env.AWIS_URL && env.AWIS_LOGIN) { // Якщо все прийшло
//                sh "mvn test -Durl=${env.AWIS_URL} -Dname=${env.AWIS_LOGIN} -DlastName=${env.AWIS_LASTNAME}"
//            } else if (env.AWIS_LOGIN && env.AWIS_LASTNAME) { // Якщо не прийшов URL
//                sh "mvn test -Dname=${env.AWIS_LOGIN} -DlastName=${env.AWIS_LASTNAME}"
//            } else if (env.AWIS_LOGIN && env.AWIS_URL) { // Якщо не прийшло прізвище
//                sh "mvn test -Durl=${env.AWIS_URL} -Dname=${env.AWIS_LOGIN}"
//            } else if (env.AWIS_URL && env.AWIS_LASTNAME) { // Якщо не прийшов логін
//                sh "mvn test -Durl=${env.AWIS_URL} -DlastName=${env.AWIS_LASTNAME}"
//            } else if (env.AWIS_LASTNAME) { // Якщо прийшло тільки прізвище
//                sh "mvn test -DlastName=${env.AWIS_LASTNAME}"
//            } else if (env.AWIS_LOGIN) { // Якщо прийшов тільки логін
//                sh "mvn test -DlastName=${env.AWIS_LOGIN}"
//            } else if (env.AWIS_URL) { // Якщо прийшов тільки URL
//                sh "mvn test -DlastName=${env.AWIS_URL}"
//            } else {
//                sh "mvn test"
//            }
        }
    }
}