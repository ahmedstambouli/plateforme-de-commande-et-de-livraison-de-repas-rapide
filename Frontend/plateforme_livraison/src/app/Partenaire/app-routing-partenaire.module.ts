import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import {RouterModule, Routes } from '@angular/router';
import { NaveBarPartenaireComponent } from './nave-bar-partenaire/nave-bar-partenaire.component';
import { ProfilePartenaireComponent } from './profile-partenaire/profile-partenaire.component';
import { ProductsComponent } from './products/products.component';
import { CommandeComponent } from './commande/commande.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { UpdateProductComponent } from './products/update-product/update-product.component';
import { ModaladdproductComponent } from './products/modaladdproduct/modaladdproduct.component';




const routes:Routes=[

  {path:"home" , component:HomeComponent,children:
    [
      { path:'ProfilePartenaire', component:ProfilePartenaireComponent},
      {path:'ProductParteniare',component:ProductsComponent},
      {path:'CommandePartenaire',component:CommandeComponent},
      {path:"update/:id",component:UpdateProductComponent}


    ]
},
]


@NgModule({
  declarations: [
    NaveBarPartenaireComponent,
    ProfilePartenaireComponent,
    HomeComponent,
    ProductsComponent,
    CommandeComponent,
    UpdateProductComponent,
    ModaladdproductComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    ToastrModule
  ],
  exports: [RouterModule]

})
export class AppRoutingPartenaireModule { }
