/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abctreinamentos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author rhuan.silva
 */
@Embeddable
public class PagamentoID implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "codcurso")
    private int codcurso;

    public PagamentoID() {
    }

    public PagamentoID(int id, int codcurso) {
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
        return codcurso;
    }

    public void setCodcurso(int codcurso) {
        this.codcurso = codcurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) codcurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagamentoID)) {
            return false;
        }
        PagamentoID other = (PagamentoID) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.codcurso != other.codcurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abctreinamentos.PagamentoPK[ id=" + id + ", codcurso=" + codcurso + " ]";
    }
    
}
