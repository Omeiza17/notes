## Creates a new project and sets the newly created project as the default working project.
gcloud projects create notes-cloud --name=notes-deployment --set-as-default

## Create a cloud storage bucket.
gsutil mb -c standard -l europe-west2 gs://cloud-notes/

## Create a production ready build of the application by running this command in the project root directory.
mvn package

## Copy the build file to the previously create bucket.
gsutil cp target/notes-0.0.1-SNAPSHOT.jar gs://cloud-notes/notes-0.0.1.jar

## Creates an instance template called notes-deployment-template
gcloud compute instance-templates create notes-deployment-template \
    --image-family debian-9 \
    --image-project debian-cloud \
    --machine-type n1-standard-2 \
    --scopes "userinfo-email,cloud-platform" \
    --metadata-from-file startup-script=instance-startup.sh \
    --metadata BUCKET=cloud-notes \
    --tags http-server

## Create an instance group of two instances using the template above.
gcloud compute instance-groups managed create notes-deployment-group \
    --base-instance-name notes-deployment-group \
    --size 2 \
    --template notes-deployment-template \
    --zone europe-west2-b

## Creates a firewall rule
gcloud compute firewall-rules create default-allow-http-8080 \
    --allow tcp:8080 \
    --source-ranges 0.0.0.0/0 \
    --target-tags http-server \
    --description "Allow port 8080 access to http-server"

## Creates a health check for the instances
gcloud compute http-health-checks create notes-health-check \
    --request-path /message \
    --port 8080

## Creates a named port
gcloud compute instance-groups managed set-named-ports notes-deployment-group \
    --named-ports http:8080 \
    --zone europe-west2-b

## Create a backend service
gcloud compute backend-services create notes-deployment-service \
    --http-health-checks notes-health-check \
    --global

## Add the instance group to the backend service
gcloud compute backend-services add-backend notes-deployment-service \
    --instance-group notes-deployment-group \
    --global \
    --instance-group-zone europe-west2-b

## Create a URL map
gcloud compute url-maps create notes-deployment-service-map \
    --default-service notes-deployment-service

## Creates a proxy that uses the created url map
gcloud compute target-http-proxies create notes-deployment-service-proxy \
    --url-map notes-deployment-service-map

## Create a global forwarding rule
gcloud compute forwarding-rules create notes-http-rule \
    --target-http-proxy notes-deployment-service-proxy \
    --ports 80 \
    --global

## auto-scaling configuration.
gcloud compute instance-groups managed set-autoscaling notes-deployment-group \
    --max-num-replicas 10 \
    --min-num-replicas 3 \
    --target-load-balancing-utilization 0.6 \
    --zone europe-west2-b
