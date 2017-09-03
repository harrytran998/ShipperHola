/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class OrderMessage implements Serializable {

    private Integer id;
    private Date date;
    private String content;
    private Account account;
    private OrderMessage repliedMessage;
    private Order order;

    public OrderMessage() {
    }

    public OrderMessage(Integer id) {
        this.id = id;
    }

    public OrderMessage(Integer id, Date date, String content, Account account, OrderMessage repliedMessage, Order order) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.account = account;
        this.repliedMessage = repliedMessage;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OrderMessage getRepliedMessage() {
        return repliedMessage;
    }

    public void setRepliedMessage(OrderMessage repliedMessage) {
        this.repliedMessage = repliedMessage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderMessage{" + "id=" + id + ", date=" + date + ", content=" + content + ", account=" + account + ", repliedMessage=" + repliedMessage + ", order=" + order + '}';
    }

}
