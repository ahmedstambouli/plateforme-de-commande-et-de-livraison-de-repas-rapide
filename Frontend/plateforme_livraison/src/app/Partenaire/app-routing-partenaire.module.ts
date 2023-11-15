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



const routes:Routes=[

  {path:"home" , component:HomeComponent,children:
    [
      { path:'ProfilePartenaire', component:ProfilePartenaireComponent},
      {path:'ProductParteniare',component:ProductsComponent},
      {path:'CommandePartenaire',component:CommandeComponent}


    ]
},
]


@NgModule({
  declarations: [
    NaveBarPartenaireComponent,
    ProfilePartenaireComponent,
    HomeComponent,
    ProductsComponent,
    CommandeComponent
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
