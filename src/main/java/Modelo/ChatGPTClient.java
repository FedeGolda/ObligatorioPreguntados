/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jmv14
 */
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatGPTClient {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final String API_KEY = "";

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public ChatGPTClient() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Pregunta generarPregunta(String categoria, String dificultad) throws IOException {

        String prompt = "Genera una pregunta de trivia estrictamente relacionada con la categoría: " + categoria + 
". La dificultad de la pregunta debe ser para alguien que " + dificultad + ".  El formato debe ser JSON con los campos 'pregunta', 'opciones' (4 respuestas posibles) y 'respuestaCorrecta' (una de las opciones).";

        // Crear el cuerpo de la solicitud para la API
        String jsonRequest = "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"" + prompt + "\"}]}";

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(jsonRequest, MediaType.parse("application/json")))
                .build();

        // Realizar la solicitud
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Error en la respuesta de la API: " + response);
            }

            // Convertir la respuesta JSON en una instancia de Pregunta
            String jsonResponse = response.body().string();
            return parsearRespuesta(jsonResponse);
        }
    }

    private Pregunta parsearRespuesta(String jsonResponse) throws IOException {
        // Leer el JSON completo de la respuesta y extraer el contenido del mensaje
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode choicesNode = rootNode.path("choices");

        if (choicesNode.isArray() && choicesNode.size() > 0) {
            String contenidoRespuesta = choicesNode.get(0).path("message").path("content").asText();

            // Parsear el contenido del mensaje como JSON para crear una instancia de Pregunta
            JsonNode preguntaNode = objectMapper.readTree(contenidoRespuesta);
            String preguntaTexto = preguntaNode.path("pregunta").asText();
            String respuestaCorrecta = preguntaNode.path("respuestaCorrecta").asText();

            // Extraer opciones como un arreglo de String
            String[] opciones = objectMapper.convertValue(
                    preguntaNode.path("opciones"),
                    String[].class
            );

            return new Pregunta(preguntaTexto, opciones, respuestaCorrecta);
        } else {
            throw new IOException("Formato inesperado en la respuesta de la API.");
        }
    }

}
