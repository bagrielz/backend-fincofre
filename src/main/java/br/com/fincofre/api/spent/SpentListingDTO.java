package br.com.fincofre.api.spent;

public record SpentListingDTO(String date, String spent, Status status, String value, Category category, String account, Method method) {

    public SpentListingDTO(Spent spent) {
        this(spent.getDate(), spent.getSpent(), spent.getStatus(), spent.getValue(), spent.getCategory(), spent.getAccount(), spent.getMethod());
    }

}
