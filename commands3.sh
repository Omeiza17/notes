# Configures the region of cloud run.
gcloud config set run/region europe-west1

# Create a pub/sub topic
gcloud pubsub topics create noteCreation

# Deploy the image
gcloud run deploy pubsub-tutorial --image gcr.io/notes-cloud/cloud-three

# Enable Pub/Sub to create authentication tokens for the deployed service.
gcloud projects add-iam-policy-binding notes-cloud \
     --member=serviceAccount:service-906676766448@gcp-sa-pubsub.iam.gserviceaccount.com \
     --role=roles/iam.serviceAccountTokenCreator

# Create or select a service account to represent the Pub/Sub subscription identity.
gcloud iam service-accounts create cloud-run-notes-invoker \
     --display-name "Cloud Run Notes Pub/Sub Invoker"

# Give the invoker service account permission to invoke your notes service
gcloud run services add-iam-policy-binding cloud-three \
   --member=serviceAccount:cloud-run-pubsub-invoker@notes-cloud.iam.gserviceaccount.com \
   --role=roles/run.invoker

# Create a Pub/Sub subscription with the service account
gcloud pubsub subscriptions create myRunSubscription --topic <Topic> \
   --push-endpoint=34.89.103.8:8080/ \
   --push-auth-service-account=cloud-run-pubsub-invoker@notes-cloud.iam.gserviceaccount.com

