import axios from "axios";
import { useCallback, useState } from "react";

const http = axios.create({
  baseURL: "http://localhost:8080/rest",
});

const useApi = () => {

  const [errorAxios, setErrorAxios] = useState({});
  
  const getData = useCallback(async (url) => {
    try {                           
      const response = await http.get(url);
      setErrorAxios(null);
      return response.data;
    } catch (error) {
      setErrorAxios(error);
    } finally {
    }
  }, []);

  const postData = useCallback( async ( url, dados ) => {
    try {
      const response = await http.post(url, dados);
      setErrorAxios(null);
      return response;
    }catch(error){
      setErrorAxios(error);
    }finally{

    }
  },[]); 

  const putData = useCallback( async ( id, url, dados ) => {
    try {
      const response = await http.put(`${url}${id}`, dados);
      setErrorAxios(null);
      return response;
    }catch(error){
      setErrorAxios(error);
    }finally{

    }
  },[]); 
     
  const getDataById = useCallback( async ( id, url ) => {
    try {
      const response = await http.get( `${url}${id}` );
      setErrorAxios(null);
      return response;
    }catch(error){
      setErrorAxios(error);
    }finally{

    }
  },[]); 


  const deleteDataById = useCallback( async ( id, url ) => {
    try {
      const response = await http.delete( `${url}${id}` );
      setErrorAxios(null);
      return response;
    }catch(error){
      setErrorAxios(error);
    }finally{

    }
  },[]); 

  return {
    errorAxios,
    getData,
    postData,
    putData,
    getDataById,
    deleteDataById,
  };
};

export default useApi;
