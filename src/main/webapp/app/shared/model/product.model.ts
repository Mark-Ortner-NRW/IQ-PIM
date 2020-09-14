import { Moment } from 'moment';

export interface IProduct {
  id?: number;
  name?: string;
  deleted?: boolean;
  createdAt?: Moment;
  modifiedAt?: Moment;
  sku?: string;
  isActive?: boolean;
  type?: string;
  amount?: number;
  createdById?: string;
  modifiedById?: string;
  productFamilyId?: string;
  nameEnUs?: string;
  nameDeDe?: string;
  productStatus?: string;
  price?: number;
  currency?: string;
  priceCurrency?: string;
  taxId?: string;
  ean?: string;
  mpn?: string;
  packagingId?: string;
  uvp?: number;
  tag?: any;
  ownerUserId?: string;
  assignedUserId?: string;
  finalPrice?: number;
  finalPriceCurrency?: string;
  longDescription?: any;
  longDescriptionDeDe?: any;
  longDescriptionEnUs?: any;
  productSerieId?: string;
  data?: any;
  catalogId?: string;
  basePriceAmount?: number;
  packedAmount?: number;
  imageId?: string;
  measuringUnitId?: string;
  deliveryStatus?: string;
  deliveryStatusDeDe?: string;
  deliveryStatusEnUs?: string;
  supplied?: any;
  suppliedDeDe?: any;
  suppliedEnUs?: any;
  brandId?: number;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public name?: string,
    public deleted?: boolean,
    public createdAt?: Moment,
    public modifiedAt?: Moment,
    public sku?: string,
    public isActive?: boolean,
    public type?: string,
    public amount?: number,
    public createdById?: string,
    public modifiedById?: string,
    public productFamilyId?: string,
    public nameEnUs?: string,
    public nameDeDe?: string,
    public productStatus?: string,
    public price?: number,
    public currency?: string,
    public priceCurrency?: string,
    public taxId?: string,
    public ean?: string,
    public mpn?: string,
    public packagingId?: string,
    public uvp?: number,
    public tag?: any,
    public ownerUserId?: string,
    public assignedUserId?: string,
    public finalPrice?: number,
    public finalPriceCurrency?: string,
    public longDescription?: any,
    public longDescriptionDeDe?: any,
    public longDescriptionEnUs?: any,
    public productSerieId?: string,
    public data?: any,
    public catalogId?: string,
    public basePriceAmount?: number,
    public packedAmount?: number,
    public imageId?: string,
    public measuringUnitId?: string,
    public deliveryStatus?: string,
    public deliveryStatusDeDe?: string,
    public deliveryStatusEnUs?: string,
    public supplied?: any,
    public suppliedDeDe?: any,
    public suppliedEnUs?: any,
    public brandId?: number
  ) {
    this.deleted = this.deleted || false;
    this.isActive = this.isActive || false;
  }
}
