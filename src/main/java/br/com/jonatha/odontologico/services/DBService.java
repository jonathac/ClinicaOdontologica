package br.com.jonatha.odontologico.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonatha.odontologico.domain.Cliente;
import br.com.jonatha.odontologico.domain.Consulta;
import br.com.jonatha.odontologico.domain.Endereco;
import br.com.jonatha.odontologico.domain.Usuario;
import br.com.jonatha.odontologico.domain.enums.Perfil;
import br.com.jonatha.odontologico.repositories.ClienteRepository;
import br.com.jonatha.odontologico.repositories.ConsultaRepository;
import br.com.jonatha.odontologico.repositories.EnderecoRepository;
import br.com.jonatha.odontologico.repositories.UsuarioRepository;

/** Classe injeção de dados no banco de dados, para testes
 * @author Jonatha Queiroz
 */

@Service
public class DBService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ConsultaRepository consultaRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfHH = new SimpleDateFormat("HH:mm");
		
		Usuario us1 = new Usuario("Climed", "climedparnamirim@gmail.com", "climedrecepcao");
		us1.addPerfil(Perfil.ADMIN);
		usuarioRepository.save(us1);
		
		Cliente cli1 = new Cliente("Jonatha Cassio", "jonathacassio@gmail.com", "123456", "09476645482", false, null, null, sdf.parse("16/02/1993"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco("59155370", "Rua estrada para Cajupiranga", "5", null, "Jardim Planalto", "Parnamirim", "RN", cli1);
		cli1.getEndereco().addAll(Arrays.asList(e1));
		
		Consulta cons1 = new Consulta(sdf.parse("30/03/2022"), sdfHH.parse("10:00"), "Manutenção Aparelho",cli1);
		cli1.getConsulta().add(cons1);
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1));
		consultaRepository.save(cons1);
			
	}
	
}
