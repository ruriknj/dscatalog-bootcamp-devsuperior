import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ProductsResponse } from '../../core/types/Product';
import { makeRequest } from '../../core/utils/request';
import ProductCard from './components/productCard';
import './styles.scss';

const Catalog = () => {

    /* quando o componente iniciar, buscar a lista de produtos;
       quando a lista de produtos estiver disponível,
       popular um estado no componente, e listar os produtos dinamicamente
     */

    const [productsResponse, setProductsResponse] = useState<ProductsResponse>();

    console.log(productsResponse);

    useEffect(() => {
        const params = {
            page: 0,
            linesPerPage: 12
        }

        /*  limitações do fetch:
            muito verboso,
            não tem suporte nativo para ler o progresso de upload de arquivos
            não tem suporte nativo para enviar queries strings
            MELHOR OPÇÃO: axios
         */
        makeRequest({ url: '/products', params })
            .then(Response => setProductsResponse(Response.data));
    }, []);

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de produtos
        </h1>
            <div className="catalog-products">

                {productsResponse?.content.map(product => (
                    <Link to="/products/1" key={product.id}>
                        <ProductCard product={product} />
                    </Link>
                ))}
            </div>
        </div>
    )
};

export default Catalog;