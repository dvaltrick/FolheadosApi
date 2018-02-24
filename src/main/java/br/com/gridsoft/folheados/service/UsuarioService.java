package br.com.gridsoft.folheados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.enumarator.Perfil;
import br.com.gridsoft.folheados.model.Usuario;
import br.com.gridsoft.folheados.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario novo(Usuario usuarioParaSalvar) throws Exception{
		Usuario salvo = null;
		
		verificarPreenchimentoUsuario(usuarioParaSalvar);
		verificarUsuarioExistente(usuarioParaSalvar);
		try{	
			usuarioParaSalvar = inicializaPerfilDefault(usuarioParaSalvar);
			salvo = repository.save(usuarioParaSalvar);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Ocorreu um problema ao gravar o usuário");
		}
		
		
		return salvo;
	}
	
	public Usuario alterar(Usuario usuarioParaSalvar) throws Exception{
		verificarPreenchimentoUsuario(usuarioParaSalvar);
		try{	
			usuarioParaSalvar = repository.save(usuarioParaSalvar);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Ocorreu um problema ao gravar o usuário");
		}
		return usuarioParaSalvar;
		
	}
	
	public void deletar(Integer idParaExcluir) throws Exception{
		try{
			repository.delete(idParaExcluir);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível excluir o usuário");
		}
	}
	
	public List<Usuario> buscarTodosUsuarios(){
		return repository.findAll();
	}
	
	public Usuario login(Usuario usuarioLogin) throws Exception{
		Usuario usuarioLogado = null;
		
		verificarPreenchimentoUsuario(usuarioLogin);
		usuarioLogado = repository.login(usuarioLogin.getNome(), usuarioLogin.getSenha());
		
		if(usuarioLogado == null){
			throw new Exception("Usuário e senha incorretos");
		}
		
		return usuarioLogado;
	}
	
	private Usuario inicializaPerfilDefault(Usuario usuarioTratamento){
		if(usuarioTratamento.getPerfil() == null){
			usuarioTratamento.setPerfil(Perfil.VENDEDOR);
		}
		
		return usuarioTratamento;
	}
	
	private void verificarUsuarioExistente(Usuario usuarioParaTeste) throws Exception{
		if(!repository.buscarUsuarioPorNome(usuarioParaTeste.getNome()).isEmpty()){
			throw new Exception("Usuário já cadastrado");
		}
	}
	
	private void verificarPreenchimentoUsuario(Usuario usuarioParaTeste) throws Exception{
		if(usuarioParaTeste.getNome().isEmpty()){
			throw new Exception("Nome de usuário deve ser informado");
		}
		
		if(usuarioParaTeste.getSenha().isEmpty()){
			throw new Exception("Senha deve ser informada");
		}
	}
	
}
