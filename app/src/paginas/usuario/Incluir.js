import React, { Fragment, useEffect, useState } from "react";
import Navegacao from "../../components/mensagem/Navegacao";
import * as FaIcons from "react-icons/fa";
import * as MdIcons from "react-icons/md";

import { BUTTON_SIZE, BUTTON_SIZE_SHOW_MESSAGE, DEFAULT_IMAGEM } from "../../config/Config";

import './usuario.css';
import useValidarDadosUsuario from "./ValidarDadosUsuario";
import { Link } from "react-router-dom";
import MensagemErro from "../../components/mensagem/MensagemErro";
import Select from "../../components/form/Select";
import { TIPO_USUARIO, USUARIO } from "../../types/Usuario";

import useApi from "../../service/AxiosService";
import DropdownSearch from "../../components/dropdown/DropdownSearch";
import { CIDADE_LISTAR, USUARIO_LISTAR, USUARIO_SALVAR } from "../../config/urlSistema";
import useRedirect from "../../hook/useRedirect";



const Incluir = () => {
  const [listaCidade, setListaCidade] = useState([]);
  const [,setIdCidade]=useState('');
  const [nomeCidade, setNomeCidade] = useState('');
  const [redirect, setRedirect ] = useState(false);
  
  const { errorAxios, getData, postData } = useApi(); 
  const {  
    usuario,
    setUsuario,
    errors,
    setErrors,
    handleChangeUsuario,
    isValid,
    validateAll,
   } = useValidarDadosUsuario();

  useRedirect(USUARIO_LISTAR,3000, redirect ); 

  useEffect(()=>{
    async function getCidade(){
      const response = await getData(CIDADE_LISTAR);
      if (response){
        setListaCidade(response);
      }
    } 
    getCidade();
  },[getData])

  const onSubmitDadosUsuario = async (e) => {
     e.preventDefault();
     let erros = validateAll();
     if (isValid(erros)){
        await handleSubmitDadosUsuario();
     } else {
       setErrors(erros);
     }
  } 

  const handleSelectCidade = (option, value ) => {
     if (option !== null){
        setIdCidade(option.idCidade);
        setNomeCidade(value);
        setUsuario((prevUsuario)=>{
          return {
            ...prevUsuario,
            idCidade: option.idCidade
          }
        })
     } else {
      setIdCidade("");
      setNomeCidade("");
     }
  }
  
  const handleSubmitDadosUsuario = async (e) => {
     console.log(usuario);

     await postData(USUARIO_SALVAR, usuario);
     if(!errorAxios){
       setUsuario(USUARIO);
       setRedirect(true);
     }
  }

  return (
    <Fragment>
      <Navegacao
        titulo="Usuário"
        iconTitulo={<FaIcons.FaUserAlt size={BUTTON_SIZE_SHOW_MESSAGE} />}
        descricao="Manutenção do cadastro de usuário"
        iconReturn={<MdIcons.MdList size={BUTTON_SIZE_SHOW_MESSAGE} />}
        caminho="Usuário"
        url="/usuario/listar"
        tituloUrl="listagem de usuários"
      />
      <div className="app-windows">
        <div className="form-profile">
          <div className="foto">
            <label className="app-label">Foto</label>
            <div className="card">
              <div className="card-body">
                <div className="d-flex flex-column align-items-center text-center">
                  <input type="hidden" id="foto" name="foto" />
                  <input type="hidden" id="contenType" name="contenType" />
                  <img src={DEFAULT_IMAGEM} className="avatar" alt="foto do usuário" />
                </div>
                <div className="fileInput">
                  <input type="file" />
                  <button
                    id="upload"
                    className="btn btn-success btn-lg upload"
                    title="Upload de foto"
                  >
                    <i>
                      <FaIcons.FaUpload size={BUTTON_SIZE} />
                    </i>
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div className="form">
            <form onSubmit={onSubmitDadosUsuario}>
              <div className="cadastro">
                <div className="form-cadastro-usuario">
                  <div className="form-group">
                    <label
                      htmlFor="codUsuario"
                      className="control-label app-label"
                    >
                      Código do Usuário:
                    </label>
                    <div className="input-group">
                      <input
                        type="text"
                        id="codUsuario"
                        name="codUsuario"
                        value={usuario.codUsuario}
                        onChange={(e)=>
                          handleChangeUsuario(e.target.name, e.target.value)
                        }
                        className={
                          errors.codUsuario
                            ? "form-control is-invalid app-label"
                            : "form-control app-label"
                        }
                      />
                      {
                        errors.codUsuario ? (
                           <MensagemErro
                              errors = {errors.codUsuario}
                              mensagens={errors.codUsuario}
                           />
                        ) : null
                      }
                    </div>
                  </div>
                  <div className="form-group">
                    <label
                      htmlFor="nomeUsuario"
                      className="control-label app-label"
                    >
                      Nome do Usuário:
                    </label>
                    <div className="input-group">
                      <input
                        type="text"
                        id="nomeUsuario"
                        name="nomeUsuario"
                        value={usuario.nomeUsuario}
                        onChange={(e)=>
                          handleChangeUsuario(e.target.name, e.target.value)
                        }
                        className={
                          errors.nomeUsuario
                            ? "form-control is-invalid app-label"
                            : "form-control app-label"
                        }
                      />
                       {
                        errors.nomeUsuario ? (
                           <MensagemErro
                              errors = {errors.nomeUsuario}
                              mensagens={errors.nomeUsuario}
                           />
                        ) : null
                      }
                    </div>
                  </div>
                  <div className="form-group">
                    <label htmlFor="email" className="control-label app-label">
                      E-mail:
                    </label>
                    <div className="input-group">
                      <input
                        type="text"
                        id="email"
                        name="email"
                        value={usuario.email}
                        onChange={(e)=>
                          handleChangeUsuario(e.target.name, e.target.value)
                        }
                        className={
                          errors.email
                            ? "form-control is-invalid app-label"
                            : "form-control app-label"
                        }
                      />
                    </div>
                  </div>
                  <div className="form-group">
                    <label htmlFor="senha" className="control-label app-label">
                      Senha:
                    </label>
                    <div className="input-group">
                      <input
                        type="password"
                        id="senha"
                        name="senha"
                        value={usuario.senha}
                        onChange={(e)=>
                          handleChangeUsuario(e.target.name, e.target.value)
                        }
                        className={
                          errors.senha
                            ? "form-control is-invalid app-label"
                            : "form-control app-label"
                        }
                      />
                    </div>
                  </div>
                  <div className="form-group">
                    <label
                      htmlFor="confirmSenha"
                      className="control-label app-label"
                    >
                      Confirme a Senha:
                    </label>
                    <div className="input-group">
                      <input
                        type="password"
                        id="confirmSenha"
                        name="confirmSenha"
                        value={usuario.confirmSenha}
                        onChange={(e)=>
                          handleChangeUsuario(e.target.name, e.target.value)
                        }
                        className={
                          errors.confirmSenha
                            ? "form-control is-invalid app-label"
                            : "form-control app-label"
                        }
                      />
                    </div>
                  </div>
                  <Select 
                       id="tipo"
                       label="Tipo Usuário"
                       value={usuario.tipo} 
                       handleChange={(name, value) => {
                          handleChangeUsuario(name, value)
                       }}
                       array={TIPO_USUARIO}
                    />
                  <div className="form-group">
                    <DropdownSearch 
                      label="Nome da Cidade:"
                      options={listaCidade}
                      search="nomeCidade"
                      id="idCidade"
                      selectedVal={nomeCidade}
                      handleChange={(option, value)=> 
                         handleSelectCidade(option, value) 
                      }
                    />
                    {/* <label
                      htmlFor="idCidade"
                      className="control-label app-label"
                    >
                      Código da Cidade:
                    </label>
                    <div className="input-group">
                      <select
                        id="idCidade"
                        name="idCidade"
                        value={usuario.idCidade}
                        onChange={handleChangeUsuario}
                        className={
                          errors.tipo
                            ? "form-control is-invalid app-label"
                            : "form-control app-label"
                        }
                      >
                        <option value="">Escolha a cidade</option>
                        <option value="1">Birigui</option>
                        <option value="2">Araçatuba</option>
                      </select>
                    </div> */}
                  </div>
                  <div></div>
                  {usuario.tipo === "2" && (
                    <>
                      <div className="form-group">
                        <label
                          htmlFor="codAluno"
                          className="control-label app-label"
                        >
                          Código do Aluno:
                        </label>
                        <div className="input-group">
                          <input
                            type="text"
                            id="codAluno"
                            name="codAluno"
                            value={usuario.codAluno}
                            onChange={(e)=>
                              handleChangeUsuario(e.target.name, e.target.value)
                            }
                            className={
                              errors.codAluno
                                ? "form-control is-invalid app-label"
                                : "form-control app-label"
                            }
                          />
                        </div>
                      </div>
                      <div className="form-group">
                        <label
                          htmlFor="idade"
                          className="control-label app-label"
                        >
                          Idade:
                        </label>
                        <div className="input-group">
                          <input
                            type="text"
                            id="idade"
                            name="idade"
                            value={usuario.idade}
                            onChange={(e)=>
                              handleChangeUsuario(e.target.name, e.target.value)
                            }
                            className={
                              errors.idade
                                ? "form-control is-invalid app-label"
                                : "form-control app-label"
                            }
                          />
                        </div>
                      </div>
                      <div className="form-group">
                        <label
                          htmlFor="nomeAluno"
                          className="control-label app-label"
                        >
                          Nome do Aluno:
                        </label>
                        <div className="input-group">
                          <input
                            type="text"
                            id="nomeAluno"
                            name="nomeAluno"
                            value={usuario.nomeAluno}
                            onChange={(e)=>
                              handleChangeUsuario(e.target.name, e.target.value)
                            }
                            className={
                              errors.nomeAluno
                                ? "form-control is-invalid app-label"
                                : "form-control app-label"
                            }
                          />
                        </div>
                      </div>
                      <div></div>
                    </>
                  )}
                  {usuario.tipo === "1" && (
                    <>
                      <div className="form-group">
                        <label
                          htmlFor="codProfessor"
                          className="control-label app-label"
                        >
                          Código do Professor:
                        </label>
                        <div className="input-group">
                          <input
                            type="text"
                            id="codProfessor"
                            name="codProfessor"
                            value={usuario.codProfessor}
                            onChange={(e)=>
                              handleChangeUsuario(e.target.name, e.target.value)
                            }
                            className={
                              errors.codProfessor
                                ? "form-control is-invalid app-label"
                                : "form-control app-label"
                            }
                          />
                        </div>
                      </div>
                      <div className="form-group">
                        <label
                          htmlFor="nomeProfessor"
                          className="control-label app-label"
                        >
                          Nome do Usuário:
                        </label>
                        <div className="input-group">
                          <input
                            type="text"
                            id="nomeProfessor"
                            name="nomeProfessor"
                            value={usuario.nomeProfessor}
                            onChange={(e)=>
                              handleChangeUsuario(e.target.name, e.target.value)
                            }
                            className={
                              errors.nomeProfessor
                                ? "form-control is-invalid app-label"
                                : "form-control app-label"
                            }
                          />
                        </div>
                      </div>
                    </>
                  )}
                  <button
                     type="submit"
                     title="Incluir dados do usuário"
                     className="btn btn-success btn-lg app-button app-label mt-2"
                  >
                     <FaIcons.FaSave size={BUTTON_SIZE}/>
                     Salvar cadastro
                  </button>
                  <Link to='/usuario/listar'
                        type="button"
                        title='Cancelar inclusão do usuário'
                        className='btn btn-warning btn-lg app-button app-label mt-2 '
                  >
                     <MdIcons.MdCancel size={BUTTON_SIZE}/>
                     Cancelar cadastro
                  </Link>

                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default Incluir;
