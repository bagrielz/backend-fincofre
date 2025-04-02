package br.com.fincofre.api.domain.spent;

import br.com.fincofre.api.domain.user.User;

public record SpentDetailsDTO(Long id, Long userId, String date, String spent, Status status, String value, Category category, String account, Method method) {

    public SpentDetailsDTO(Spent spent) {
        this(spent.getId(), spent.getUser().getId(), spent.getDate(), spent.getSpent(), spent.getStatus(), spent.getValue(), spent.getCategory(), spent.getAccount(), spent.getMethod());
    }

}
