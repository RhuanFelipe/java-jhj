package com.abctreinamentos;
// Generated 19/09/2019 11:10:06 by Hibernate Tools 4.3.1

/**
 * PagamentoId generated by hbm2java
 */
public class PagamentoId implements java.io.Serializable {

    private static final long SerialVersionUID = 1L;
    private int id;
    private int codcurso;

    public PagamentoId() {
    }

    public PagamentoId(int id, int codcurso) {
        this.id = id;
        this.codcurso = codcurso;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodcurso() {
        return this.codcurso;
    }

    public void setCodcurso(int codcurso) {
        this.codcurso = codcurso;
    }
}
