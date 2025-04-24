# Makefile for clean-architecture-example project
# This Makefile automates the process of building, deploying, and managing the application in Minikube

# Variables
IMAGE_NAME := clean-architecture-app
IMAGE_TAG := 1.0
FULL_IMAGE_NAME := $(IMAGE_NAME):$(IMAGE_TAG)

# Default target
.PHONY: all
all: build docker-build minikube-load deploy

# Build the application with Maven
.PHONY: build
build:
	@echo "Building application with Maven..."
	mvn clean package

# Build Docker image
.PHONY: docker-build
docker-build:
	@echo "Building Docker image $(FULL_IMAGE_NAME)..."
	docker build -t $(FULL_IMAGE_NAME) .

# Load Docker image into Minikube
.PHONY: minikube-load
minikube-load:
	@echo "Loading Docker image $(FULL_IMAGE_NAME) into Minikube..."
	minikube image load $(FULL_IMAGE_NAME)

# Deploy application to Minikube
.PHONY: deploy
deploy:
	@echo "Deploying application to Minikube..."
	kubectl apply -k kubernetes/

# Start Minikube if not already running
.PHONY: minikube-start
minikube-start:
	@echo "Starting Minikube..."
	minikube start
	minikube addons enable ingress

# Setup hosts file for Ingress (Windows PowerShell)
.PHONY: setup-hosts
setup-hosts:
	@echo "Setting up hosts file for Ingress..."
	@echo "Please run the following command as Administrator in PowerShell:"
	@echo "Add-Content -Path 'C:\Windows\System32\drivers\etc\hosts' -Value \"$$(minikube ip) clean-architecture.local\""

# Clean up resources
.PHONY: clean
clean:
	@echo "Cleaning up resources..."
	kubectl delete -k kubernetes/ || true
	docker rmi $(FULL_IMAGE_NAME) || true

# Show application URL
.PHONY: show-url
show-url:
	@echo "Application URL: http://clean-architecture.local/api/v1/comptes"
	@echo "Minikube IP: $(shell minikube ip)"

# Help target
.PHONY: help
help:
	@echo "Available targets:"
	@echo "  all            - Build application, create Docker image, load into Minikube, and deploy"
	@echo "  build          - Build application with Maven"
	@echo "  docker-build   - Build Docker image"
	@echo "  minikube-load  - Load Docker image into Minikube"
	@echo "  deploy         - Deploy application to Minikube"
	@echo "  minikube-start - Start Minikube and enable Ingress"
	@echo "  setup-hosts    - Setup hosts file for Ingress"
	@echo "  clean          - Clean up resources"
	@echo "  show-url       - Show application URL"
	@echo "  help           - Show this help message"
