package br.com.fincofre.api.spent;

public record SpentResponseDTO(String date, String spent, Status status, String value, Category category, String account, Method method) {
}
