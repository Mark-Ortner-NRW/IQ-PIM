import { Moment } from 'moment';
import { IProduct } from 'app/shared/model/product.model';

export interface IBrand {
  id?: number;
  name?: string;
  deleted?: boolean;
  description?: any;
  isActive?: boolean;
  createdAt?: Moment;
  modifiedAt?: Moment;
  createdById?: string;
  modifiedById?: string;
  nameEnUs?: string;
  descriptionEnUs?: any;
  nameDeDe?: string;
  descriptionDeDe?: any;
  code?: string;
  ownerUserId?: string;
  assignedUserId?: string;
  products?: IProduct[];
}

export class Brand implements IBrand {
  constructor(
    public id?: number,
    public name?: string,
    public deleted?: boolean,
    public description?: any,
    public isActive?: boolean,
    public createdAt?: Moment,
    public modifiedAt?: Moment,
    public createdById?: string,
    public modifiedById?: string,
    public nameEnUs?: string,
    public descriptionEnUs?: any,
    public nameDeDe?: string,
    public descriptionDeDe?: any,
    public code?: string,
    public ownerUserId?: string,
    public assignedUserId?: string,
    public products?: IProduct[]
  ) {
    this.deleted = this.deleted || false;
    this.isActive = this.isActive || false;
  }
}
