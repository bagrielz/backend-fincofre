package br.com.fincofre.api.spent;

public record SpentListingDTO(String date, String spent, Status status, String value, Category category, String account, Method method) {

    public SpentListingDTO(Spent data) {
        this(data.getDate(), data.getSpent(), data.getStatus(), data.getValue(), data.getCategory(), data.getAccount(), data.getMethod());
    }

}
