package br.com.fincofre.api.domain.spent;

public record SpentDetailsDTO(Long id, String date, String spent, Status status, String value, Category category, String account, Method method) {

    public SpentDetailsDTO(Spent spent) {
        this(spent.getId(), spent.getDate(), spent.getSpent(), spent.getStatus(), spent.getValue(), spent.getCategory(), spent.getAccount(), spent.getMethod());
    }

}
