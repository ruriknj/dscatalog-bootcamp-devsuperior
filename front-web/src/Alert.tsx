import React from 'react';

type Props = {
    text?: string //a interogação faz ficar opcional a variável text no App
}
const Alert = ( {text}: Props ) => (
<div className="alert alert-primary">
    Hello {text}!
</div>
);

export default Alert;