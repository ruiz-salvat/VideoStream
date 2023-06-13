package org.example.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Subscription {

    public Subscription(Long lastPaymentTimestamp, Long validUntilTimestamp, SubscriptionType subscriptionType) {
        this.lastPaymentTimestamp = lastPaymentTimestamp;
        this.validUntilTimestamp = validUntilTimestamp;
        this.subscriptionType = subscriptionType;
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long lastPaymentTimestamp;

    private Long validUntilTimestamp;

    @ManyToOne
    @JoinColumn(name = "subscription_type_id")
    private SubscriptionType subscriptionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLastPaymentTimestamp(), that.getLastPaymentTimestamp()) && Objects.equals(getValidUntilTimestamp(), that.getValidUntilTimestamp()) && Objects.equals(getSubscriptionType(), that.getSubscriptionType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastPaymentTimestamp(), getValidUntilTimestamp(), getSubscriptionType());
    }
}
