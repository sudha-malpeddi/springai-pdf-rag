# SpringAI PDF RAG

SpringAI PDF RAG is a document query system that extracts content from PDF files and leverages a Retrieval-Augmented Generation (RAG) approach to answer user queries. Built with **Spring Boot** and **SpringAI**, it ensures efficient processing and context-aware responses with page-level attribution.

## Features

- **PDF Extraction**: Reads PDF files using `spring-ai-pdf-document-reader`.
- **Embedded Database**: Stores indexed PDF data using an inbuilt embedded database.
- **AI-Driven Queries**: Provides AI-generated responses with context from the document.
- **Attribution**: Includes page-level details in responses for transparency.
- **RESTful API**: Easy integration with a well-defined API.

## Tech Stack

- **Framework**: Spring Boot 3.4.0
- **AI Integration**: SpringAI
- **Database**: Inbuilt embedded database
- **PDF Processing**: `spring-ai-pdf-document-reader`
- **Build Tool**: Maven

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/sudha-malpeddi/springai-pdf-rag.git
   cd springai-pdf-rag
