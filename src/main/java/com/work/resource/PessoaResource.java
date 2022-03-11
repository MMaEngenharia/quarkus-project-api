package com.work.resource;

import com.work.business.PessoaBusiness;
import com.work.model.Pessoa;
import com.work.repository.PessoaRepository;

import javax.ws.rs.Path;

@Path("/pessoa")
public class PessoaResource extends BaseResource<Pessoa, PessoaRepository, PessoaBusiness> {

}