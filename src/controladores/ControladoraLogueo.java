/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.NivelDeUsuario;

import modelo.Permiso;

import modelo.Usuario;
import persistencia.ControladoraPersistencia;
import persistencia.NivelDeUsuarioJpaController;
import persistencia.PermisoJpaController;
import persistencia.UsuarioJpaController;

/**
 *
 * @author EcobarM
 */
public class ControladoraLogueo extends ControladoraPersistencia {

    private UsuarioJpaController usuarioJpa;
    private NivelDeUsuarioJpaController nivelJPA;
    private PermisoJpaController permisoJPA;

    public UsuarioJpaController getUsuarioJpaController() {
        return usuarioJpa;
    }

    public void setUsuarioJpaController(UsuarioJpaController usuarioJpa) {
        this.usuarioJpa = usuarioJpa;
    }

    public ControladoraLogueo() {

        this.nivelJPA = new NivelDeUsuarioJpaController(getEmf());
        this.permisoJPA = new PermisoJpaController(getEmf());
        this.usuarioJpa = new UsuarioJpaController(getEmf());

    }

    public void registarUsuario(Usuario usuario) throws Exception {
        try {
            this.usuarioJpa.create(usuario);
        } catch (Exception e) {
            throw new Exception(" El usuario " + usuario.getNombreUsuario() + " ya existe");
        }
    }

    public void modificarUsuario(Usuario usuario) throws Exception {
        try {
            this.usuarioJpa.edit(usuario);
        } catch (Exception e) {
            throw new Exception("El usuario " + usuario.getNombreUsuario() + " ya existe");
        }

    }

    public Usuario buscarXnombreUsuario(String nombreUsuario) throws Exception {
        return this.usuarioJpa.buscarPorNombreUsuario(nombreUsuario);
    }

    public List<Usuario> filtrarXnombreUsuario(String nombreUsuario) {
        return this.usuarioJpa.FiltrarPorNombreUsuario(nombreUsuario);
    }

    public List<NivelDeUsuario> filtrarXnombreNivel(String nombreNivel) {
        return this.nivelJPA.FiltrarPorNombreNivel(nombreNivel);
    }

    public void registrarNivel(NivelDeUsuario nivel) throws Exception {
        try {
            this.nivelJPA.create(nivel);
        } catch (Exception e) {
            throw new Exception("El nivel " + nivel.getNombre() + " ya existe");
        }

    }

    public NivelDeUsuario registrarPrimerNivel(NivelDeUsuario nivel) {

        try {
            this.nivelJPA.create(nivel);
            return nivel;
        } catch (Exception e) {
            return null;
        }

    }

    public void modificarNivel(NivelDeUsuario nivel) throws Exception {
        try {
            this.nivelJPA.edit(nivel);
        } catch (Exception e) {
            throw new Exception("El nivel " + nivel.getNombre() + " ya existe ");
        }

    }

    public NivelDeUsuario buscarNivelPorCodigo(int codigo) {
        return this.nivelJPA.findNivelDeUsuario(codigo);

    }

    public Permiso buscarPermisoPorCodigo(int codigo) {
        return this.permisoJPA.findPermiso(codigo);
    }

    public void modificarPermiso(Permiso permiso) throws Exception {
        try {
            this.permisoJPA.edit(permiso);

        } catch (Exception e) {
            throw new Exception("No se puede modificar el permiso ");
        }
    }

    public Permiso registarPermiso(Permiso permiso) throws Exception {
        try {
            this.permisoJPA.create(permiso);
            return permiso;
        } catch (Exception e) {
            return null;
        }
    }

    public List<NivelDeUsuario> listarNiveles() {

        return this.nivelJPA.findNivelDeUsuarioEntities();
    }

    public List<Usuario> listarUsuario() {
        return this.usuarioJpa.findUsuarioEntities();
    }

    public int getUsuarios() {
        return this.usuarioJpa.getUsuarioCount();

    }

    public Usuario buscarUsuarioPorCodigo(int codigo) {

        return this.usuarioJpa.findUsuario(codigo);
    }

}
