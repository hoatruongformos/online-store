import dayjs from 'dayjs/esm';

import { InvoiceStatus } from 'app/entities/enumerations/invoice-status.model';
import { PaymentMethod } from 'app/entities/enumerations/payment-method.model';

import { IInvoice, NewInvoice } from './invoice.model';

export const sampleWithRequiredData: IInvoice = {
  id: 91509,
  date: dayjs('2022-11-21T16:54'),
  status: InvoiceStatus['ISSUED'],
  paymentMethod: PaymentMethod['CREDIT_CARD'],
  paymentDate: dayjs('2022-11-22T04:51'),
  paymentAmount: 24487,
  code: 'Expressway Berkshire',
};

export const sampleWithPartialData: IInvoice = {
  id: 32294,
  date: dayjs('2022-11-21T10:24'),
  details: 'Venezuela Arkansas auxiliary',
  status: InvoiceStatus['ISSUED'],
  paymentMethod: PaymentMethod['PAYPAL'],
  paymentDate: dayjs('2022-11-21T22:48'),
  paymentAmount: 28832,
  code: 'Buckinghamshire Florida Wooden',
};

export const sampleWithFullData: IInvoice = {
  id: 16008,
  date: dayjs('2022-11-21T13:46'),
  details: 'Chief Security',
  status: InvoiceStatus['PAID'],
  paymentMethod: PaymentMethod['CASH_ON_DELIVERY'],
  paymentDate: dayjs('2022-11-21T22:13'),
  paymentAmount: 88279,
  code: 'background',
};

export const sampleWithNewData: NewInvoice = {
  date: dayjs('2022-11-21T07:59'),
  status: InvoiceStatus['ISSUED'],
  paymentMethod: PaymentMethod['CREDIT_CARD'],
  paymentDate: dayjs('2022-11-21T18:15'),
  paymentAmount: 80001,
  code: 'Romania Borders',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
