/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abctreinamentos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author rhuan.silva
 */
@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codcurso")
    private Integer codcurso;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "valor")
    private long valor;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;

    public Curso() {
    }

    public Curso(Integer codcurso) {
        this.codcurso = codcurso;
    }

    public Curso(Integer codcurso, String nome, long valor, String url) {
        this.codcurso = codcurso;
        this.nome = nome;
        this.valor = valor;
        this.url = url;
    }

    public Integer getCodcurso() {
        return codcurso;
    }

    public void setCodcurso(Integer codcurso) {
        this.codcurso = codcurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcurso != null ? codcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.codcurso == null && other.codcurso != null) || (this.codcurso != null && !this.codcurso.equals(other.codcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curso {codigo="+codcurso+", nome=" + nome + ", valor = " + valor + ", url = " + url + "}";
    }
    
}
