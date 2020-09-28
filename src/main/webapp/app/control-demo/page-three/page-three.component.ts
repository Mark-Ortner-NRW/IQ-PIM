import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Brand, IBrand } from 'app/shared/model/brand.model';
import { BrandService } from 'app/entities/brand/brand.service';
import { HttpResponse } from '@angular/common/http';
import { RippleModule } from 'primeng/ripple';
import { PrimeNGConfig } from 'primeng/api';
import { MessageService } from 'primeng/api';

// import { FilterUtils } from 'primeng/utils';
// import { LazyLoadEvent } from 'primeng/api';
// import { SelectItem } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';

@Component({
  selector: 'jhi-page-one',
  templateUrl: './page-three.component.html',
  providers: [MessageService, ConfirmationService],
  styleUrls: ['page-three.component.scss'],
})
export class PageThreeComponent implements OnInit {
  color: any = '#24244c';
  brands?: IBrand[] | any;
  brands2?: IBrand[] | any;
  brand: Brand = {};

  _selectedColumns: any[] = [];

  productDialog: any = false;

  @ViewChild('dt') table: Table | undefined;

  loading: any = true;

  cols: any[] = [
    { field: 'id', header: 'ID' },
    { field: 'name', header: 'Name' },
    { field: 'description', header: 'Description' },
    { field: 'createdAt', header: 'Created At' },
  ];
  clonedBrands: { [n: number]: Brand } = {};

  constructor(
    protected brandService: BrandService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private primengConfig: PrimeNGConfig
  ) {}
  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.brandService.query({}).subscribe(
      (res: HttpResponse<IBrand[]>) => this.onSuccess(res.body),
      () => this.onError()
    );

    this._selectedColumns = this.cols;
    this.loading = false;
  }

  @Input() get selectedColumns(): any[] {
    return this._selectedColumns;
  }

  set selectedColumns(val: any[]) {
    // restore original order
    this._selectedColumns = this.cols.filter(col => val.includes(col));
  }

  protected onSuccess(data: IBrand[] | null): void {
    this.brands = data || [];
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Got Data ... .' });
  }

  protected onError(): void {}

  public onEditBrand(brand: Brand): void {
    this.brands = { ...brand };
    this.productDialog = true;
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Product has been updated' });
  }

  public onDeleteBrand(brand: Brand): void {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + brand.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        // this.brands = this.brands.filter(val => val.id !== brand.id);
        this.brand = {};
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
      },
    });

    delete this.brandService[brand.id || 0];
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Brand has been deleted' });

    this.brands = { ...brand };
    this.productDialog = true;
  }
}
