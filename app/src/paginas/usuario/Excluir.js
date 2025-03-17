
import React, { Fragment, useEffect, useState } from "react";
import Navegacao from "../../components/mensagem/Navegacao";
import * as FaIcons from "react-icons/fa";
import * as MdIcons from "react-icons/md";

import { BUTTON_SIZE, BUTTON_SIZE_SHOW_MESSAGE, DANGER, DEFAULT_IMAGEM } from "../../config/Config";

import './usuario.css';
import useValidarDadosUsuario from "./ValidarDadosUsuario";
import { Link, useParams } from "react-router-dom";

import { TIPO_USUARIO, USUARIO } from "../../types/Usuario";

import useApi from "../../service/AxiosService";

import { USUARIO_BUSCAR_POR_ID, USUARIO_DELETE, USUARIO_LISTAR } from "../../config/urlSistema";
import useRedirect from "../../hook/useRedirect";
import Button from "../../components/form/Button";



const Excluir = () => {

  const { idUsuario } = useParams();

  const [redirect, setRedirect ] = useState(false);
  
  const { errorAxios, getDataById, deleteDataById } = useApi(); 
  const {  
    usuario,
    setUsuario,
   } = useValidarDadosUsuario();

  useRedirect(USUARIO_LISTAR,3000, redirect ); 

  useEffect(()=>{
    async function getUsuario(){
      if(idUsuario){
        const response = await getDataById(idUsuario, USUARIO_BUSCAR_POR_ID);
        if (response){
          setUsuario(response.data);
        }
      }
    }
    getUsuario();
  },[idUsuario, getDataById, setUsuario])


  const onSubmitDadosUsuario = async (e) => {
     e.preventDefault();
     handleSubmitDadosUsuario();
  } 

  
  const handleSubmitDadosUsuario = async (e) => {
     console.log(usuario);
     await deleteDataById(idUsuario, USUARIO_DELETE);
     if(!errorAxios){
       setUsuario(USUARIO);
       setRedirect(true);
     }
  }

  const tipoUsuario = 
       TIPO_USUARIO.find(
         (item) => item.value === usuario.tipo)?.name || "";

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
                        defaultValue={usuario.codUsuario}
                        className="form-control app-label"
                      />
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
                        defaultValue={usuario.nomeUsuario}
                        className="form-control app-label"
                      />
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
                        defaultValue={usuario.email}
                        className="form-control app-label"
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
                        defaultValue={usuario.senha}
                        className="form-control app-label"
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
                        defaultValue={usuario.confirmSenha}
                        className="form-control app-label"
                      />
                    </div>
                  </div>
                  <div className="form-group">
                    <label
                      htmlFor="tipo"
                      className="control-label app-label"
                    >
                      Tipo Usuário:
                    </label>
                  <input 
                       id="tipo"
                       defaultValue={tipoUsuario} 
                       className="form-control app-label"
                  />
                  </div>   
                  <div className="form-group">
                     <label
                      htmlFor="confirmSenha"
                      className="control-label app-label"
                     >
                      Cidade:
                    </label>
                    <input 
                      id="idCidade"
                      defaultValue={usuario.nomeCidade}
                      className="form-control app-label"
                    />
                  </div>
                  <div></div>
                  {String(usuario.tipo) === "2" && (
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
                            defaultValue={usuario.codAluno}
                            className="form-control app-label"
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
                            defaultValue={usuario.idade}
                            className="form-control app-label"
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
                            defaultValue={usuario.nomeAluno}
                            className="form-control app-label"
                          />
                        </div>
                      </div>
                      <div></div>
                    </>
                  )}
                  {String(usuario.tipo) === "1" && (
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
                            defaultValue={usuario.codProfessor}
                            className="form-control app-label"
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
                            defaultValue={usuario.nomeProfessor}
                            className="form-control app-label"
                          />
                        </div>
                      </div>
                    </>
                  )}
                  <Button
                     id="buttomSumit"
                     type="submit"
                     title="Excluir dados do usuário"
                     variant={DANGER}
                     cssClass="btn btn-success btn-lg app-button app-label mt-2"
                     label="Excluir Cadastro"
                     icon={<FaIcons.FaTrashAlt size={BUTTON_SIZE}/>}>
                  </Button>
                  <Link to='/usuario/listar'
                        type="button"
                        title='Cancelar exclusão do usuário'
                        className='btn btn-warning btn-lg app-button app-label mt-2 '
                  >
                     <MdIcons.MdCancel size={BUTTON_SIZE}/>
                     Cancelar exclusão
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

export default Excluir;
