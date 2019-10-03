/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abctreinamentos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author rhuan.silva
 */
@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PagamentoID id;
    @Column(name = "datainscricao")
    private String datainscricao;

    public Pagamento() {
    }

    public Pagamento(PagamentoID id) {
        this.id = id;
    }

    public Pagamento(PagamentoID id, String datainscricao) {
        this.id = id;
        this.datainscricao = datainscricao;
    }

    public PagamentoID getPagamentoPK() {
        return id;
    }

    public void setPagamentoPK(PagamentoID id) {
        this.id = id;
    }

    public String getDatainscricao() {
        return datainscricao;
    }

    public void setDatainscricao(String datainscricao) {
        this.datainscricao = datainscricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pagamento {id=" + id.getId()+ ", codcurso=" + id.getCodcurso() + ", data inscrição = " + datainscricao + "}";
    }
    
}
