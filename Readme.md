ConfigServer

* Use SpringCloud technology
* ConfigServer is connected with a Github account which has all repositories with the configurations.
* Implemented a system to manage users and permisions to access to the repositories 

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
    * git_account
    * profile_spring_cloud - (dev, qa, prod, {profile})
    * directory_for_root_configs
