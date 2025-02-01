package br.com.fincofre.api.spent;

public record SpentListingDTO(String date, String spent, Status status, String value, Category category, String account, Method method) {
}
