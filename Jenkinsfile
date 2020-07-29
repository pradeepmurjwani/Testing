node {
   try {
      stage('initialize') {
         def dockerHome = tool 'docker' 
         def mavenHome  = tool 'maven-3.6.3'
         def sonarqubeScannerHome = tool 'sonarqube-scanner' 
         env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}:${sonarqubeScannerHome}/bin"
      }

      stage('git checkout') {
         git "https://github.com/pradeepmurjwani/Testing.git"
      }

      stage ('code clean, test and build') {
         sh "mvn clean package"
      }

      stage('SonarQube analysis') {
        // withSonarQubeEnv('sonarqube-server') { 
         //   sh "sonar-scanner"
         // }
         sh "mvn sonar:sonar"
      }
      
      stage ('docker build') {
         sh "sudo docker build -t pradeepmurjwani/testing:0.1 ."
      }

      stage ('docker image push into dockerhub') {
          withCredentials([string(credentialsId: 'dockerHubPassword', variable: 'dockerHubPassword')]) {
               sh "sudo docker login -u pradeepmurjwani -p ${dockerHubpassword}"
         }
         sh "sudo docker push pradeepmurjwani/testing:0.1"
      }
   } catch(e) {
      currentBuild.result = 'FAILURE'
      notifyFailed()
   }
} 

def notifyFailed() {
   def toEmailAddress = "pradeep.m.murjwani@gmail.com"
   def subject = "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
   def body = """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>"""
   
   emailext body: "${body}", mimeType: 'text/html', replyTo: 'pradeep.m.murjwani@gmail.com', subject: "${subject}", to: "${toEmailAddress}"
}
