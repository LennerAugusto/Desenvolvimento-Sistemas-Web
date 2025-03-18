package com.academico.models.service.dto.response;

import java.time.LocalDateTime;

public class Mensagem<T> {
    private String mensagem; // Alterado para minúscula
    private T dados;
    private String path;
    private LocalDateTime data;
    private String metodo;
    private Integer status;

    private Mensagem(Builder<T> builder) {
        this.status = builder.status;
        this.mensagem = builder.mensagem;
        this.path = builder.path;
        this.data = builder.data;
        this.metodo = builder.metodo;
    }

    // Getters e setters
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    // Classe Builder
    public static class Builder<T> {
        private String mensagem;
        private T dados;
        private String path;
        private LocalDateTime data;
        private String metodo;
        private Integer status;

        public Builder<T> status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder<T> dados(T dados) {
            this.dados = dados;
            return this;
        }

        public Builder<T> path(String path) {
            this.path = path;
            return this;
        }

        public Builder<T> data(LocalDateTime data) {
            this.data = data;
            return this;
        }

        public Builder<T> metodo(String metodo) {
            this.metodo = metodo;
            return this;
        }

        public Mensagem<T> build() {
            return new Mensagem<T>(this);
        }
    }


//    public String toString() {
//        return "Mensagem{" +
//                "mensagem='" + mensagem + '\'' +
//                ", dados=" + dados +
//                ", path='" + path + '\'' +
//                ", data=" + data +
//                ", metodo='" + metodo + '\'' +
//                ", status=" + status +
//                '}';
//    }
}
