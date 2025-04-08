import { Fragment } from "react";
import { Link } from "react-router-dom";

const ShowItem = (path,icon,page,idex) => {
    return (
        <Fragment>
            <li className="list" key={index}>
                <Link to={path}>
                    <i>{icon}</i>
                    <span className="link">{page}</span>
                </Link>
            </li>
        </Fragment>
    )
}

export default ShowItem