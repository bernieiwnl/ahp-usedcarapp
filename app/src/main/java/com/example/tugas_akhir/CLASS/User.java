package com.example.tugas_akhir.CLASS;

public class User {
    private String idUser;
    private String nama_lengkap;
    private String email;
    private String password;
    private String idFoto;
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    public User(){
        this.setIdUser("");
        this.setNama_lengkap("");
        this.setEmail("");
        this.setPassword("");
        this.setIdFoto("");
    }

}
