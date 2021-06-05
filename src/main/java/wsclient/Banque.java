/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsclient;

/**
 *
 * @author bensakina
 */
public class Banque {

    private Long nbanque;
    private float ncompte;
    private float ncompteeuro;

    public Banque() {
    }

    public Banque(Long nbanque, float ncompte, float ncompteeuro) {
        this.nbanque = nbanque;
        this.ncompte = ncompte;
        this.ncompteeuro = ncompteeuro;
    }

    public Long getNbanque() {
        return nbanque;
    }


    public float getNcompte() {
        return ncompte;
    }

    public void setNcompte(float ncompte) {
        this.ncompte = ncompte;
    }

    public float getNcompteeuro() {
        return ncompteeuro;
    }

    public void setNcompteeuro(float ncompteeuro) {
        this.ncompteeuro = ncompteeuro;
    }

    @Override
    public String toString() {
        return "Banque{" + "nbanque=" + nbanque + ", ncompte=" + ncompte + ", ncompteeuro=" + ncompteeuro + '}';
    }


    public void setNbanque(long nbanque) {
        this.nbanque = nbanque;
    }
}