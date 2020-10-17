import React from 'react';
import {ReactComponent as ArrowIcon} from '../../assets/images/arrow.svg'
import './styles.scss'

type Props = {
    text?: string;
}

const Buttonicon = ({text}:Props ) => (
// d-flex ->coloca um elemento ao lado do outro
    <div className="d-flex">     
        <button className="btn btn-primary btn-icon">
        <h5>{text}</h5>

    </button>
    <div className="btn-icon-content">
        <ArrowIcon />
    </div>
    </div>
)
export default Buttonicon