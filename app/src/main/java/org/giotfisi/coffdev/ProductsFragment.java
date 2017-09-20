package org.giotfisi.coffdev;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.giotfisi.coffdev.Models.Product;

import java.util.ArrayList;
import java.util.List;




public class ProductsFragment extends Fragment {

    private List<Product> listaproducts;
    private RecyclerView recyclerView;
    private AdapterProduct AdaptadorProductos;
    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listaproducts=obtenerListaProductos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.myrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdaptadorProductos=new AdapterProduct(listaproducts);
        recyclerView.setAdapter(AdaptadorProductos);
        return rootView;
    }
    public List<Product> obtenerListaProductos(){
        List<Product> listap=new ArrayList<>();
        listap.add(new Product("Cappuccino espresso","El balance perfecto. Nuestra carga de espresso acompañada con abundante espuma de leche",R.drawable.capuccino));
        listap.add(new Product("Café Latte","Leche cremosa y espresso, ligeramente recubierto con suave espuma de leche",R.drawable.caffelatte));
        listap.add(new Product("Vanilla Latte","Delicioso café espresso con leche caliente y dulces toques de vainilla. Todo esto coronado con una sutil capa de espuma de leche",R.drawable.vanillalatte));
        listap.add(new Product("Café Mocha","Delicioso e intenso chocolate, espresso y leche cremosa, coronado con crema batida. Irresistible para los días fríos",R.drawable.cafemocha));
        listap.add(new Product("Café Mocha Blanco","Irresistible combinación de mocha blanco con nuestra carga de espresso y leche caliente, coronado con crema batida",R.drawable.cafemochablanco));
        listap.add(new Product("Caramel Macchiato","Leche cremosa “manchada” con espresso y vainilla, cubierta con una deliciosa rejilla de caramelo. Dulce, cremoso e intenso",R.drawable.caramelm));
        listap.add(new Product("Espresso","Es la verdadera esencia del café en la forma más concentrada. El espresso de CoffDev es intenso y con un toque acaramelado",R.drawable.espresso));
        listap.add(new Product("Té Chai Latte","Deliciosa mezcla especiada de té Chai, leche y una delgada capa de espuma de leche",R.drawable.techailatte));
        listap.add(new Product("Té Latte","Disfruta una delicioso Earl Grey o un Vanilla Rooibos combinado con leche",R.drawable.telatte));
        return listap;
    }
}
