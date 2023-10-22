import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import {RouterModule, Routes } from '@angular/router';
import { NaveBarPartenaireComponent } from './nave-bar-partenaire/nave-bar-partenaire.component';
import { ProfilePartenaireComponent } from './profile-partenaire/profile-partenaire.component';
import { ProductsComponent } from './products/products.component';


const routes:Routes=[

  {path:"home" , component:HomeComponent,children:
    [
      { path:'ProfilePartenaire', component:ProfilePartenaireComponent},
      {path:'ProductParteniare',component:ProductsComponent}


    ]
},
]


@NgModule({
  declarations: [
    NaveBarPartenaireComponent,
    ProfilePartenaireComponent,
    HomeComponent,
    ProductsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]

})
export class AppRoutingPartenaireModule { }
