# AI Summarizer

A Spring Boot application that leverages AI to generate concise summaries of articles and web content. It uses Spring AI with Ollama to process HTML content from URLs and provide summarized outputs.

## Features

- **URL Extraction**: Automatically extracts URLs from input text messages
- **HTML Fetching**: Retrieves HTML content from provided URLs
- **Content Sanitization**: Strips HTML tags to extract clean text content
- **AI-Powered Summarization**: Uses Ollama with Llama 3.1 model to generate concise summaries
- **REST API**: Provides endpoints for summary generation
- **Scheduled Digests**: Includes a cron job for daily summary digests

## Prerequisites

- Java 25
- Gradle
- Ollama server running locally or remotely with the `llama3.1:8b-instruct-q4_K_M` model

## Setup

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd AISummarizer
   ```

2. **Configure Ollama**:
   - Ensure Ollama is installed and running
   - Pull the required model:
     ```bash
     ollama pull llama3.1:8b-instruct-q4_K_M
     ```
   - Update `src/main/resources/application.yaml` if your Ollama server is not at `http://192.168.0.217:11434`

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run the application**:
   ```bash
   ./gradlew bootRun
   ```

The application will start on the default Spring Boot port (8080).

## Usage

### API Endpoints

#### Generate Summary
**POST** `/api/generate-summary`

Generates a summary from URLs found in the input message.

**Request Body**:
```json
{
  "message": "Check out this article: https://example.com/article and this one: https://another.com/post"
}
```

**Response**: A string containing the AI-generated summary of the content from the URLs.

#### General Generation
**POST** `/api/generate`

Sends a general message to the AI model for processing.

**Request Body**:
```json
{
  "message": "Your prompt here"
}
```

**Response**: The AI model's response to the prompt.

### Example Usage

```bash
curl -X POST http://localhost:8080/api/generate-summary \
  -H "Content-Type: application/json" \
  -d '{"message": "Summarize this: https://en.wikipedia.org/wiki/Artificial_intelligence"}'
```

## Configuration

The application uses `application.yaml` for configuration:

- `spring.ai.ollama.base-url`: URL of the Ollama server
- `spring.ai.ollama.chat.options.model`: The AI model to use
- `prompts.summaryPrompt`: The prompt template for summary generation

## Dependencies

- Spring Boot 4.0.2
- Spring AI 2.0.0-M2 (Ollama integration)
- JSoup for HTML parsing
- Lombok for boilerplate code reduction

## Architecture

- **Controller**: `SummaryController` handles API requests
- **Service**: `HtmlService` manages URL extraction, HTML fetching, and sanitization
- **Provider**: `HtmlProvider` and `HtmlClient` handle HTTP requests
- **Configuration**: `PromptsConfig` manages AI prompts

## Development

To run tests:
```bash
./gradlew test
```

To build a native image with GraalVM:
```bash
./gradlew nativeCompile
```

## License

[Add your license here]
