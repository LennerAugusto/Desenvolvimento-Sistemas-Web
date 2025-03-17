import { useEffect } from "react";
import { useNavigate } from "react-router-dom"

const useRedirect = (
  to, 
  delay=3000,
  condition = true
) => {

  const navigate = useNavigate();

  useEffect(()=>{
     if (!condition) return;

     const timer = setTimeout(() => {
          navigate(to);      
     }, delay);
     
     return () => clearTimeout(timer);

    },[navigate, to, delay, condition]);

}

export default useRedirect;