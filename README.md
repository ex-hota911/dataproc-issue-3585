Reproduce https://github.com/GoogleCloudPlatform/google-cloud-java/issues/3585

```bash
$ export GOOGLE_APPLICATION_CREDENTIALS=/path/to/credential.json
$ ./gradlew run --args "$PROJECT_ID $CLUSTER_NAME"
```
