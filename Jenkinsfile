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
         withSonarQubeEnv('sonarqube-server') { 
            //sh "mvn sonar:sonar"
            sh "sonar-scanner"
          }
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
   //to email address (multiple email address can be put by comma seperated
   def toEmailAddress = "pradeep.m.murjwani@gmail.com"
   
   //cc email address (multiple email address can be put by comma seperated
   def ccEmailAddress = "wantsomegetsome@gmail.com,itsspy_2050@yahoo.com"
   
   //email extension plugin requires every cc keyword for multiple cc email address(like cc:<email-1>, cc:<email-2>...), so that's the below logic is written
   def ccEmailAddressArray = ccEmailAddress.split(",")
   def finalccEmailAddress = ""
   for (int i = 0; i < ccEmailAddressArray.size(); i++) { 
      finalccEmailAddress = finalccEmailAddress + (ccEmailAddressArray[i].trim())
      if (i != (ccEmailAddressArray.size()-1)) {
         finalccEmailAddress = finalccEmailAddress + "," + "cc:"
      }
   }
   
   //subject
   def subject = "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
   
   //body
   def body = """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>"""
   
   //email extension plugin method
   emailext body: "${body}", mimeType: 'text/html', subject: "${subject}", to: "${toEmailAddress}, cc:${finalccEmailAddress}"
}
