import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ProductsResponse } from 'core/types/Product';
import { makeRequest } from 'core/utils/request';
import ProductCard from './components/productCard';
import ProductCardLoader from './components/Loaders/ProductCardLoader';
import './styles.scss';
import Pagination from 'core/components/Pagination';

const Catalog = () => {

    /* quando o componente iniciar, buscar a lista de produtos;
       quando a lista de produtos estiver disponível,
       popular um estado no componente, e listar os produtos dinamicamente
     */

    const [productsResponse, setProductsResponse] = useState<ProductsResponse>();
    const [isloading, setIsLoading] = useState(false);
    const [activePage, setActivePage] = useState(0);


    // console.log(productsResponse);

    useEffect(() => {
        const params = {
            page: activePage,
            linesPerPage: 12
        }
        // iniciar o loader
        setIsLoading(true);
        /*  limitações do fetch:
            muito verboso,
            não tem suporte nativo para ler o progresso de upload de arquivos
            não tem suporte nativo para enviar queries strings
            MELHOR OPÇÃO: axios
         */
        makeRequest({ url: '/products', params })
            .then(Response => setProductsResponse(Response.data))
            .finally(() => {
                //finalizar o loader
                setIsLoading(false);
            })
    }, [activePage]);

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de produtos
        </h1>
            <div className="catalog-products">
                {isloading ? <ProductCardLoader /> : (
                    productsResponse?.content.map(product => (
                        <Link to={`/products/${product.id}`} key={product.id}>
                            <ProductCard product={product} />
                        </Link>
                    ))
                )}
            </div>
            {productsResponse && (
                < Pagination
                    totalPages={productsResponse.totalPages}
                    activePage={activePage}
                    onChange={page => setActivePage(page) }
                />
            )}
        </div>
    )
};

export default Catalog;