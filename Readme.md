ConfigServer

* Use SpringCloud technology
* ConfigServer is connected with a Github account which has all repositories with the configurations.
* Security
    * Implemented a system to manage users and permisions to access to the repositories
    * Basic authentication
    * Without authentication
    
    
Security

    Basic Authentication:
      Put these env. variables
      
        * enable_basic_security= true
        * basic_security_user=someUsername
        * basic_security_password=somePassword
    
    Without authentication:
          Put these env. variables
          
            * enable_basic_security=false
            * basic_security_user=none
            * basic_security_password=none
            
    Authentication with roles
    
        Put these env. variables
              
                * enable_basic_security=false
                * basic_security_user=none
                * basic_security_password=none 
                * directory_for_root_configs = placeOfTheConfigsFolder
        
        In this project we have a template to be used as a sample.
        
        To add a new microservice configuration:
        
        1. Add a folder with the name of the microservice down of the folder resources/configs
        2. Each folder should have two files:
            * users.yml -> Indicate the credentials and their roles
            * environments.yml -> Indicate the URI and roles which can access to the URI.

Mini-Client in python:

In the same folder of the project


python attack_url.py --url "http://localhost:8888/your_url" --user youruser --pass yourpass


Environment variables:

    * server_port
    
    * enable_basic_security
    * basic_security_user
    * basic_security_password
    
    * git_account
    * git_user_name
    * git_user_password
    * profile_spring_cloud - (dev, qa, prod, {profile})
    
    * directory_for_root_configs
    
Docker runs:

Without authentication

    docker run -e directory_for_root_configs=configs_a -e git_account=https://github.com/geekOramon/ -e server_port=8888 -e profile_spring_cloud={profile} -e enable_basic_security=false -e basic_security_user=none -e basic_security_password=none --net=host -d -p 8888:8888 springio/configuration-microservice

With basic authentication (user: Goku Password: SSj3)

    docker run -e directory_for_root_configs=configs_a -e git_account=https://github.com/geekOramon/ -e server_port=8888 -e profile_spring_cloud={profile} -e enable_basic_security=true -e basic_security_user=Goku -e basic_security_password=SSj3 --net=host -p 8888:8888 springio/configuration-microservice
    
Use configs to filter users and environments

    docker run -e directory_for_root_configs=configs -e git_account=https://github.com/geekOramon/ -e server_port=8888 -e profile_spring_cloud={profile} -e enable_basic_security=true -e basic_security_user=Goku -e basic_security_password=SSj3 --net=host -p 8888:8888 springio/configuration-microservice
    
