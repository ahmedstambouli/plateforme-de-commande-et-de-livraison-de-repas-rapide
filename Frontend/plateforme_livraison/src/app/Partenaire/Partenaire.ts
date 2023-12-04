export class PartenaireFORM{
  name!:string
  email!:string
  password!:string
  tel!:string
  adresse!:string
  role!:"partenaire"
}

export class Partenaire{

  name!:string
  email!:string
  password!:string
  tel!:string
  adresse!:string
  role!:string
  logo!:string
  id!: number
  etat!:number;
}
